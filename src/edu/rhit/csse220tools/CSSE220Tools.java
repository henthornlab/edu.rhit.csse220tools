package edu.rhit.csse220tools;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;


/**
 * Super Simple Eclipse IDE plugin that just displays a single 
 * web page with nothing else. Set the HOMEPAGE variable to where 
 * you want users to be directed to when they start the plugin
 */


public class CSSE220Tools extends ViewPart {


	public static final String HOMEPAGE = "https://henthornlab.github.io/javaguide/start/start.html";

	public static final String ID = "edu.rhit.csse220tools";

	private Browser browser;
    private Action homeAction;
    private Action backAction;
    private Action forwardAction;


    public void createPartControl(Composite parent) {
        parent.setLayout(new FillLayout());
        try {
            browser = new Browser(parent, SWT.NONE);
            // Check if it opened IE. If so, close it and force EDGE on Windows machines
            if (browser.getBrowserType().equals("ie")) {
            	browser.close();
            	browser = new Browser(parent, SWT.EDGE);
            }
            
            browser.setUrl(HOMEPAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        createToolbar();
    }
    
    private void createToolbar() {
        homeAction = new Action("Home") {
            @Override
            public void run() {
                if (browser != null && !browser.isDisposed()) {
                    browser.setUrl(HOMEPAGE);
                }
            }
        };
        homeAction.setToolTipText("Home");
        //homeAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER));

        
        homeAction.setImageDescriptor(
        	    org.eclipse.jface.resource.ImageDescriptor.createFromURL(
        	        getClass().getResource("/icons/r.png")
        	    )
        	);
        
        backAction = new Action("Back") {
            @Override
            public void run() {
                if (browser != null && browser.isBackEnabled()) {
                    browser.back();
                }
            }
        };
        backAction.setToolTipText("Back");
        backAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_BACK));

        forwardAction = new Action("Forward") {
            @Override
            public void run() {
                if (browser != null && browser.isForwardEnabled()) {
                    browser.forward();
                }
            }
        };
        forwardAction.setToolTipText("Forward");
        forwardAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD));

        IActionBars bars = getViewSite().getActionBars();
        IToolBarManager toolbar = bars.getToolBarManager();
        toolbar.add(homeAction);
        toolbar.add(backAction);
        toolbar.add(forwardAction);
    }
    
    public void setFocus() {
        if (browser != null && !browser.isDisposed()) {
            browser.setFocus();
        }
    }
}
