package pw.cdmi.core.spring;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(int.class, new FixCustomNumberEditor(Integer.class, true));    
		registry.registerCustomEditor(long.class, new FixCustomNumberEditor(Long.class, true));
		registry.registerCustomEditor(float.class, new FixCustomNumberEditor(Float.class, true));
		registry.registerCustomEditor(double.class, new FixCustomNumberEditor(Double.class, true));
	}

}
