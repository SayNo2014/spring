/*
 * Copyright 2002-2016 the original author or authors.
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

package org.springframework.web.servlet.view;

/**
 * Abstract base class for template view resolvers, in particular for FreeMarker views.
 *
 * <p>Provides a convenient way to specify {@link AbstractTemplateView}'s exposure
 * flags for request attributes, session attributes, and Spring's macro helpers.
 *
 * @author Juergen Hoeller
 * @since 1.1
 * @see AbstractTemplateView
 * @see org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver
 */
public class AbstractTemplateViewResolver extends UrlBasedViewResolver {

	private boolean exposeRequestAttributes = false;

	private boolean allowRequestOverride = false;

	private boolean exposeSessionAttributes = false;

	private boolean allowSessionOverride = false;

	private boolean exposeSpringMacroHelpers = true;


	@Override
	protected Class<?> requiredViewClass() {
		return AbstractTemplateView.class;
	}

	/**
	 * Set whether all request attributes should be added to the
	 * model prior to merging with the template. Default is "false".
	 * @see AbstractTemplateView#setExposeRequestAttributes
	 */
	public void setExposeRequestAttributes(boolean exposeRequestAttributes) {
		this.exposeRequestAttributes = exposeRequestAttributes;
	}

	/**
	 * Set whether HttpServletRequest attributes are allowed to override (hide)
	 * controller generated model attributes of the same name. Default is "false",
	 * which causes an exception to be thrown if request attributes of the same
	 * name as model attributes are found.
	 * @see AbstractTemplateView#setAllowRequestOverride
	 */
	public void setAllowRequestOverride(boolean allowRequestOverride) {
		this.allowRequestOverride = allowRequestOverride;
	}

	/**
	 * Set whether all HttpSession attributes should be added to the
	 * model prior to merging with the template. Default is "false".
	 * @see AbstractTemplateView#setExposeSessionAttributes
	 */
	public void setExposeSessionAttributes(boolean exposeSessionAttributes) {
		this.exposeSessionAttributes = exposeSessionAttributes;
	}

	/**
	 * Set whether HttpSession attributes are allowed to override (hide)
	 * controller generated model attributes of the same name. Default is "false",
	 * which causes an exception to be thrown if session attributes of the same
	 * name as model attributes are found.
	 * @see AbstractTemplateView#setAllowSessionOverride
	 */
	public void setAllowSessionOverride(boolean allowSessionOverride) {
		this.allowSessionOverride = allowSessionOverride;
	}

	/**
	 * Set whether to expose a RequestContext for use by Spring's macro library,
	 * under the name "springMacroRequestContext". Default is "true".
	 * @see AbstractTemplateView#setExposeSpringMacroHelpers
	 */
	public void setExposeSpringMacroHelpers(boolean exposeSpringMacroHelpers) {
		this.exposeSpringMacroHelpers = exposeSpringMacroHelpers;
	}


	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		AbstractTemplateView view = (AbstractTemplateView) super.buildView(viewName);
		// exposeRequestAttributes：是否将所有RequestAttributes暴露给View使用,默认为false
		view.setExposeRequestAttributes(this.exposeRequestAttributes);
		// allowRequestOverride:当RequestAttributes中存在Model同名的参数时,是否允许使用
		// RequestAttributes中的值将Model中的值覆盖,默认为false
		view.setAllowRequestOverride(this.allowRequestOverride);
		// exposeSessionAttributes：是否将SessionAttributes暴露给View使用,默认为false
		view.setExposeSessionAttributes(this.exposeSessionAttributes);
		// SessionOverride:当SessionAttributes中存在Model同名的参数时,是否允许使用
		// SessionAttributes中的值将Model中的值覆盖,默认为false
		view.setAllowSessionOverride(this.allowSessionOverride);
		// exposeSpringMacroHelpers:使用将SpringContext提供给view
		view.setExposeSpringMacroHelpers(this.exposeSpringMacroHelpers);
		return view;
	}

}
