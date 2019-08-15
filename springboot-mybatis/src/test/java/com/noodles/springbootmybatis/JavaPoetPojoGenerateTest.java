package com.noodles.springbootmybatis;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.noodles.springbootmybatis.generate.FieldDescBean;
import com.noodles.springbootmybatis.generate.util.GenerateUtil;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * @filename JavaPoetTest
 * @description JavaPoetTest
 * @author 巫威
 * @date 2019/8/15 14:18
 */
public class JavaPoetPojoGenerateTest {

	public static void main(String[] args) throws Exception {

		String targetDirectory = "springboot-mybatis/src/main/java/";
		String packageName = "com.noodles.springbootmybatis.generate";
		String className = "Student";

		// 1.获取属性
		List<FieldDescBean> fieldDescBeans = Arrays.asList(new FieldDescBean("name", "java.lang.String", "姓名"),
				new FieldDescBean("age", "java.lang.Integer", "年龄"),
				new FieldDescBean("sex", "java.lang.Boolean", "性别"));
		List<FieldSpec> fieldSpecList = getFields(fieldDescBeans);

		// 获取方法
		List<MethodSpec> methods = getSetterMethodSpec(fieldDescBeans);

		Long serialVersionUID = GenerateUtil.getRandomLong();
		CodeBlock codeBlock = CodeBlock.builder().add(serialVersionUID + "L").build();
		FieldSpec fieldSpec = FieldSpec.builder(long.class, "serialVersionUID")
				.addModifiers(Modifier.PRIVATE, Modifier.FINAL, Modifier.STATIC).initializer(codeBlock).build();
		TypeSpec student = TypeSpec.classBuilder(className).addModifiers(Modifier.PUBLIC).addFields(fieldSpecList)
				.addMethods(methods).addSuperinterface(ClassName.get(Serializable.class)).addField(fieldSpec)
				.build();

		// 4. 构建Java源文件
		JavaFile javaFile = JavaFile.builder(packageName, student).build();

		// 5. 输出java源文件到文件系统
		File dir = new File(targetDirectory);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		javaFile.writeTo(dir); // JavaFile.write(), 参数为源码生成目录(源码的classpath目录)

		System.out.println("done");

	}

	private static List<MethodSpec> getSetterMethodSpec(List<FieldDescBean> fieldDescBeanList) {
		List<MethodSpec> methodSpecList = new ArrayList<>();
		fieldDescBeanList.forEach(
				fieldDescBean -> {
					try {
						MethodSpec setMethod = MethodSpec
								.methodBuilder(GenerateUtil.getSetter(fieldDescBean.getFieldName()))
								.addModifiers(Modifier.PRIVATE)
								.addParameter(
										ParameterSpec.builder(Class.forName(fieldDescBean.getFieldType()),
												fieldDescBean.getFieldName()).build())
								.addCode(
										CodeBlock
												.builder()
												.addStatement("this.$N=$N", fieldDescBean.getFieldName(),
														fieldDescBean.getFieldName()).build()).build();
						methodSpecList.add(setMethod);

						MethodSpec getMethod = MethodSpec
								.methodBuilder(GenerateUtil.getGetter(fieldDescBean.getFieldName()))
								.addModifiers(Modifier.PRIVATE)
								.returns(Class.forName(fieldDescBean.getFieldType()))
								.addStatement("return $N", fieldDescBean.getFieldName()).build();
						methodSpecList.add(getMethod);
					} catch (ClassNotFoundException e) {
						throw new RuntimeException(e);
					}
				});
		return methodSpecList;
	}

	private static List<FieldSpec> getFields(List<FieldDescBean> fieldDescBeanList) {
		List<FieldSpec> fieldSpecList = new ArrayList<>();

		fieldDescBeanList.forEach(fieldDescBean -> {
			try {
				FieldSpec fieldSpec = FieldSpec
						.builder(Class.forName(fieldDescBean.getFieldType()), fieldDescBean.getFieldName(),
								Modifier.PRIVATE).addJavadoc(fieldDescBean.getFieldComment()).build();
				fieldSpecList.add(fieldSpec);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		return fieldSpecList;

	}

}
