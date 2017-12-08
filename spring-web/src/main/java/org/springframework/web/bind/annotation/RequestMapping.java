/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * Annotation for mapping web requests onto specific handler classes and/or
 * handler methods.
 *
 * <p>Handler methods annotated with this annotation can have very flexible
 * signatures. The exact details of the supported method arguments and return
 * values depend on the specific
 * {@link org.springframework.stereotype.Controller @Controller} model supported.
 * Both Spring Web MVC and Spring WebFlux support this annotation with some
 * differences. More details are available in the Spring Framework reference.
 *
 * <p><b>NOTE:</b> {@code @RequestMapping} will only be processed if an
 * an appropriate {@code HandlerMapping}-{@code HandlerAdapter} pair
 * is configured. If you are defining custom {@code HandlerMappings} or
 * {@code HandlerAdapters}, then you need to add {@code RequestMappingHandlerMapping}
 * and {@code RequestMappingHandlerAdapter} to your configuration.</code>.
 *
 * <p><b>NOTE:</b> When using controller interfaces (e.g. for AOP proxying),
 * make sure to consistently put <i>all</i> your mapping annotations - such as
 * {@code @RequestMapping} and {@code @SessionAttributes} - on
 * the controller <i>interface</i> rather than on the implementation class.
 *
 * @author Juergen Hoeller
 * @author Arjen Poutsma
 * @author Sam Brannen
 * @since 2.5
 * @see GetMapping
 * @see PostMapping
 * @see PutMapping
 * @see DeleteMapping
 * @see PatchMapping
 * @see RequestParam
 * @see RequestAttribute
 * @see PathVariable
 * @see ModelAttribute
 * @see SessionAttribute
 * @see SessionAttributes
 * @see InitBinder
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
 * @see org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerAdapter
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface RequestMapping {

	// mapping name
	String name() default "";

	// ģʽ����·������(��õ�һ��������)�����磺/pets
	@AliasFor("path")
	String[] value() default {};

	// spring 4.2�Ժ���������,����ʹ��ͨ���,���磺"/myPath.do"
	@AliasFor("value")
	String[] path() default {};

	// ���󷽷�ö�ٶ�������(֧��GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE)
	RequestMethod[] method() default {};

	// ����������ʽ����,ʹ�þ���,
	// 1.params="myParam=myValue",������ڲ���myParam,����ֵΪmyValue.
	// 2.params="myParam",������ڲ���myParam.
	// 3.params="!myParam",���벻���ڲ���myParam
	String[] params() default {};

	// ͷ�ֶα��ʽ����,ʹ�þ���,
	// 1.headers="myHeader=myValue"	�������ͷ�ֶ�myHeader,����ֵΪmyValue
	String[] headers() default {};

	// �������ݷ�ý����������,����������ֻ����Content-TypeֵΪapplication/json������
	// 1.consumes="application/json"
	String[] consumes() default {};

	// Ӧ��ý����������,����������ֻ����AcceptֵΪapplication/json������
	// 1.produces="application/json"
	String[] produces() default {};

}
