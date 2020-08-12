package model;

import java.util.EventListener;

import container.ConversionEvent;

// This is the interface for the Model Listener
public interface ModelListener extends EventListener {
	public void ConversionEventOccurred(ConversionEvent e);
}
