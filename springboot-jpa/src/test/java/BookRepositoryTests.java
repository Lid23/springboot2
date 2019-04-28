import com.noodles.springbootjpa.SpringbootJpaApplication;
import com.noodles.springbootjpa.bean.AuthorBean;
import com.noodles.springbootjpa.bean.BookBean;
import com.noodles.springbootjpa.repository.AuthorRepository;
import com.noodles.springbootjpa.repository.BookRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Before
    public void init() {
        AuthorBean lewis = new AuthorBean("Lewis");
        AuthorBean mark = new AuthorBean("Mark");
        AuthorBean peter = new AuthorBean("Peter");

        BookBean spring = new BookBean("Spring in Action");
        spring.getAuthorBeanSet().addAll(Arrays.asList(lewis, mark));

        BookBean springboot = new BookBean("Spring Boot in Action");
        springboot.getAuthorBeanSet().addAll(Arrays.asList(lewis, peter));

        bookRepository.save(spring);
        //bookRepository.save(springboot);
    }

    @After
    public void deleteAll() {
        // 删除所有书籍，级联删除关联的作者，但是没有与书籍关联的作者不会被删掉
        // bookRepository.deleteAll();

        // 删除所有作者，只能删除没有与书籍关联的作者，与书籍有关联的作者无法被删除
        //authorRepository.deleteAll();
    }

    @Test
    public void findAll() {
        assertThat(bookRepository.findAll()).hasSize(2);

        assertThat(authorRepository.findAll()).hasSize(3);
    }

    @Test
    public void findByName() {
        //assertThat(bookRepository.findByName("Spring in Action")).isNotNull();
        //assertThat(authorRepository.findByName("Lewis")).isNotNull();

        BookBean book = bookRepository.findByName("Spring in Action");

        System.out.println(book.getAuthorBeanSet().size());
    }

    @Test
    public void findByNameContaining() {
        assertThat(bookRepository.findByNameContaining("Spring")).hasSize(2);

        assertThat(authorRepository.findByUsernameContaining("e")).hasSize(2);
    }

    @Test
    public void margeBook() {
        BookBean book = bookRepository.findByName("Spring in Action");
        assertThat(book).isNotNull();

        book.setName("Spring in Action (4th Edition)");
        bookRepository.save(book);

        assertThat(bookRepository.findByName("Spring in Action")).isNull();
        assertThat(bookRepository.findByName("Spring in Action (4th Edition)")).isNotNull();
    }

    @Test
    public void deleteBook() {
        BookBean book = bookRepository.findByName("Spring Boot in Action");
        assertThat(book).isNotNull();

        bookRepository.delete(book);

        assertThat(bookRepository.findAll()).hasSize(1);
        assertThat(bookRepository.findByName("Spring Boot in Action")).isNull();

        assertThat(authorRepository.findAll()).hasSize(2);
        assertThat(authorRepository.findByUsername("Peter")).isNull();
    }

    @Test
    public void plusAuthor() {
        BookBean book = bookRepository.findByName("Spring in Action");
        assertThat(book).isNotNull();

        AuthorBean author = authorRepository.findByUsername("Jacob");
        assertThat(author).isNull();

        book.getAuthorBeanSet().add(new AuthorBean("Jacob"));
        bookRepository.save(book);

        assertThat(bookRepository.findByName("Spring in Action").getAuthorBeanSet()).hasSize(3);

        assertThat(authorRepository.findAll()).hasSize(4);
        assertThat(authorRepository.findByUsername("Jacob")).isNotNull();
    }

    @Test
    public void clearAuthor() {
        BookBean book = bookRepository.findByName("Spring in Action");
        assertThat(book).isNotNull();

        book.getAuthorBeanSet().clear();
        bookRepository.save(book);

        assertThat(bookRepository.findAll()).hasSize(2);
        assertThat(bookRepository.findByName("Spring in Action").getAuthorBeanSet()).isEmpty();

        assertThat(authorRepository.findAll()).hasSize(3);
    }

    @Test
    public void removeAuthor() {
        BookBean book = bookRepository.findByName("Spring Boot in Action");
        assertThat(book).isNotNull();

        AuthorBean author = authorRepository.findByUsername("Peter");
        assertThat(author).isNotNull();

        book.getAuthorBeanSet().remove(author);
        bookRepository.save(book);

        assertThat(bookRepository.findAll()).hasSize(2);
        assertThat(bookRepository.findByName("Spring Boot in Action").getAuthorBeanSet()).hasSize(1);

        assertThat(authorRepository.findAll()).hasSize(3);
        assertThat(authorRepository.findByUsername("Peter")).isNotNull();
    }

    @Test
    public void removeAllautors() {
        BookBean book = bookRepository.findByName("Spring in Action");
        assertThat(book).isNotNull();

        book.getAuthorBeanSet().removeAll(book.getAuthorBeanSet());
        bookRepository.save(book);

        assertThat(bookRepository.findAll()).hasSize(2);
        assertThat(bookRepository.findByName("Spring in Action").getAuthorBeanSet()).isEmpty();

        assertThat(authorRepository.findAll()).hasSize(3);
    }

    @Test
    public void deleteAuthor() {
        AuthorBean author = authorRepository.findByUsername("Peter");
        assertThat(author).isNotNull();

        authorRepository.delete(author);

        assertThat(bookRepository.findAll()).hasSize(2);
        assertThat(bookRepository.findByName("Spring in Action").getAuthorBeanSet()).hasSize(2);
        assertThat(bookRepository.findByName("Spring Boot in Action").getAuthorBeanSet()).hasSize(2);

        assertThat(authorRepository.findAll()).hasSize(3);
        assertThat(authorRepository.findByUsername("Peter")).isNotNull();
    }

    @Test
    public void deleteAllAuthors() {
        authorRepository.deleteAll();

        assertThat(bookRepository.findAll()).hasSize(2);
        assertThat(bookRepository.findByName("Spring in Action").getAuthorBeanSet()).hasSize(2);
        assertThat(bookRepository.findByName("Spring Boot in Action").getAuthorBeanSet()).hasSize(2);

        assertThat(authorRepository.findAll()).hasSize(3);
    }

}
