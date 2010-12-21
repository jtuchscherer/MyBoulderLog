package org.myboulderlog.client.inject;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import org.myboulderlog.client.widget.MainApplicationWidget;

@GinModules(ApplicationClientModule.class)
public interface ApplicationGinjector extends Ginjector {
    MainApplicationWidget getMainApplicationPanel();    
}
