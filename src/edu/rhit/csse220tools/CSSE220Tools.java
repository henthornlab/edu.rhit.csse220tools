package edu.rhit.csse220tools;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.FillLayout;


/**
 * Super Simple Eclipse IDE plugin that just displays a single 
 * web page with nothing else. Set the HOMEPAGE variable to where 
 * you want users to be directed to when they start the plugin
 */


public class CSSE220Tools extends ViewPart {


	public static final String HOMEPAGE = "https://henthornlab.github.io/javaguide/start/start.html";

	public static final String ID = "edu.rhit.csse220tools";

    private Browser browser;


    public void createPartControl(Composite parent) {
        parent.setLayout(new FillLayout());
        browser = new Browser(parent, SWT.NONE);
        browser.setUrl(HOMEPAGE); // Set the initial URL
    }

    public void setFocus() {
        if (browser != null && !browser.isDisposed()) {
            browser.setFocus();
        }
    }
}
