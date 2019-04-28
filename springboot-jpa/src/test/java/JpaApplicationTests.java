import com.noodles.springbootjpa.SpringbootJpaApplication;
import com.noodles.springbootjpa.bean.UserBean;
import com.noodles.springbootjpa.repository.UserJPA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class JpaApplicationTests {

	@Autowired
	public UserJPA userJPA;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testAddUser(){
		UserBean userBean = new UserBean();
		userBean.setName("测试");
		userBean.setAddress("测试地址");
		userBean.setAge(21);
		UserBean userEntity1 = userJPA.save(userBean);
		System.out.println(userEntity1.getAddress());
	}

	@Test
	public void testDeleteUser(){

	}

	@Test
	public void testGetUserByName(){

	}
}
