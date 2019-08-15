package com.noodles.springbootmybatis;

import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * @filename JavaPoetTest
 * @description JavaPoetTest
 * @author 巫威
 * @date 2019/8/15 14:18
 */
public class JavaPoetTest {

	public static void main(String[] args) {
		testJavaPoet();
		System.out.println("done");
	}

	/**
	 * 库: https://github.com/square/javapoet/
	 * 使用下面语句引用javapoet (仓库为jcenter):
	 * compile 'com.squareup:javawriter:2.5.1'
	 *
	 * 使用javapoet生成java源文件的步骤 (1,2,3步骤可以交换):
	 * 1. 构建成员变量
	 * 2. 构建构造方法
	 * 3. 构建方法(static/concrete)
	 * 4. 构建类型(enum/annotation/interface/class)
	 * 5. 构建java源文件
	 * 6. 输出java源文件到文件系统
	 */
	private static void testJavaPoet() {
		String packageName = "com.noodles.springbootmybatis.generate";
		String className = "HelloWorld";

		//1. 生成一个字段
		FieldSpec fieldSpec = FieldSpec.builder(String.class, "var", Modifier.PUBLIC).build();

		//2. 生成一个方法 (方式一: 面向代码, 更为底层的构建方式)
		MethodSpec mainMethod = MethodSpec.methodBuilder("main")  //设置方法名称
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC)   //添加修饰符
				.addParameter(String[].class, "args")             //添加参数
				.returns(TypeName.VOID)                           //添加返回值
				.addStatement("$T.out.println($S)", System.class, "Hello world !")  //添加代码语句 (结束语句的分号不需要, 注意与CodeBlock的区别)
				.build();

		//2. 生成一个方法 (方式二: 对方法建模, 结构化的构建)
		ParameterSpec parameterSpec = ParameterSpec.builder(String[].class, "args").build();  //构建参数模型
		CodeBlock codeBlock = CodeBlock.of("$T.out.println($S);", System.class, "Hello world"); //构建代码块 (语句结束的分号不能少)
		MethodSpec methodSpec = MethodSpec.methodBuilder("main")    //设置方法名称
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC)     //添加修饰符
				.returns(TypeName.VOID)                             //添加返回值
				.addParameter(parameterSpec)                        //添加方法参数
				.addCode(codeBlock)                                 //添加代码块
				.build();

		//生成个get方法
		

		//3. 生成类型(enum/class/annotation/interface)
		TypeSpec hellworld = TypeSpec.classBuilder("HelloWorld")
				.addModifiers(Modifier.PUBLIC)
				.addField(fieldSpec)
				.addMethod(mainMethod)
				.build();

		//4. 构建Java源文件
		JavaFile javaFile = JavaFile.builder(packageName, hellworld).build();

		//5. 输出java源文件到文件系统
		try {
			//生成java源文件到AndroidStudio的当前Module中
			generateToCurrentAndroidStudioModule(javaFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成到当前module的源文件目录下
	 *
	 * @param javaFile
	 * @throws IOException
	 */
	private static void generateToCurrentAndroidStudioModule(JavaFile javaFile) throws IOException {
		String targetDirectory = "springboot-mybatis/src/main/java/"; //输出到和用例程序相同的源码目录下
		File dir = new File(targetDirectory);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		javaFile.writeTo(dir); //JavaFile.write(), 参数为源码生成目录(源码的classpath目录)
	}
}
