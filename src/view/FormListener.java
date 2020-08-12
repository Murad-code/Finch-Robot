package view;

import java.util.EventListener;

import container.FormEvent;

// This is the interface for Form Listener
public interface FormListener extends EventListener {
	public void FormEventOccurred(FormEvent e);
}
