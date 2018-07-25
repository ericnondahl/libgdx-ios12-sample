package com.iostest.game;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.*;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

public class IOSLauncher extends IOSApplication.Delegate implements INativeDialog {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        MyGdxGame game = new MyGdxGame();
        game.setDialog(this);
        return new IOSApplication(game, config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }

	@Override
	public void showDialog() {
		final UIAlertController controller = new UIAlertController("Test Title", "Some info", UIAlertControllerStyle.Alert);
		
		UIAlertAction action1 = new UIAlertAction("Button 1", UIAlertActionStyle.Default, null);
		UIAlertAction action2 = new UIAlertAction("Button 2", UIAlertActionStyle.Default, null);
		
		controller.addAction(action1);
		controller.addAction(action2);

		UIApplication.getSharedApplication().getKeyWindow().getRootViewController().presentViewController(controller, true, null);
	}

	@Override
	public void hideDialog() {
		UIApplication.getSharedApplication().getKeyWindow().getRootViewController().dismissViewController(true, null);
	}
}