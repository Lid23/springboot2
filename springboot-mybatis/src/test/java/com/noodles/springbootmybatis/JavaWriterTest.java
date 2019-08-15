package com.noodles.springbootmybatis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.EnumSet;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javawriter.JavaWriter;

/**
 * @filename JavaWriterTest
 * @description JavaWriterTest
 * @author 巫威
 * @date 2019/8/15 10:59
 */
public class JavaWriterTest {

	public static void main(String[] args) throws IOException {
		testJavaWriter();
		System.out.println("done");
	}

	/**
	 * javawriter的github地址: https://github.com/square/javapoet/tree/javawriter_2
	 * 使用下面语句引用该库 (仓库为jcenter):
	 * compile 'com.squareup:javapoet:1.7.0'
	 * 使用JavaWriter生成java源文件
	 * @throws IOException
	 */
	private static void testJavaWriter() throws IOException {
		String packageName = "com.noodles.springbootmybatis.generate";
		String className = "GenerateClass";
		File outFile = new File("springboot-mybatis/src/main/java/" + packageName.replaceAll("\\.", "/") + "/" + className + ".java");
		if(!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
		}
		if (!outFile.exists()) {
			outFile.createNewFile();
		}
		System.out.println(outFile.getAbsolutePath());
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outFile));
		JavaWriter jw = new JavaWriter(writer);
		jw.emitPackage(packageName)
				.beginType(packageName + "." + className, "class", EnumSet.of(Modifier.PUBLIC, Modifier.FINAL))
				.emitField("String", "firstName", EnumSet.of(Modifier.PRIVATE))
				.emitField("String", "lastName", EnumSet.of(Modifier.PRIVATE))
				.emitJavadoc("Return the person's full name")
				.beginMethod("String", "getName", EnumSet.of(Modifier.PUBLIC))
				.emitStatement("return firstName + \" - \" + lastName")
				.endMethod()
				.beginMethod("String", "getFirstName", EnumSet.of(Modifier.PUBLIC))
				.emitStatement("return firstName")
				.endMethod()
				.beginMethod("String", "getLastName", EnumSet.of(Modifier.PUBLIC))
				.emitStatement("return lastName") //注意不要使用分号结束return语句
				.endMethod()
				.endType()
				.close();
	}






}
