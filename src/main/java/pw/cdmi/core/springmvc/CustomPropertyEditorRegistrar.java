package pw.cdmi.core.springmvc;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(int.class, new FixCustomNumberEditor(Integer.class, true));    
		registry.registerCustomEditor(long.class, new FixCustomNumberEditor(Long.class, true));
		registry.registerCustomEditor(float.class, new FixCustomNumberEditor(Float.class, true));
		registry.registerCustomEditor(double.class, new FixCustomNumberEditor(Double.class, true));
	}

}
