package pw.cdmi.core.spring;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class CustomWebBindingInitializer implements WebBindingInitializer {
	
//	@Autowired
//	private CustomPropertyEditorRegistrar customPropertyEditorRegistrar;

	
	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
//		customPropertyEditorRegistrar.registerCustomEditors(binder);
        binder.registerCustomEditor(int.class, new FixCustomNumberEditor(Integer.class, true));    
        binder.registerCustomEditor(long.class, new FixCustomNumberEditor(Long.class, true));
        binder.registerCustomEditor(float.class, new FixCustomNumberEditor(Float.class, true));
        binder.registerCustomEditor(double.class, new FixCustomNumberEditor(Double.class, true));
	}

}
