package viewmodel;

import javafx.beans.property.*;
import model.*;

import java.text.spi.NumberFormatProvider;

public class ManageExerciseViewModel {
    private StringProperty errorProperty;
    private StringProperty headerProperty;
    private BooleanProperty completedProperty;
    private StringProperty topicProperty;
    private IntegerProperty numberProperty;
    private IntegerProperty sessionProperty;
    private BooleanProperty editableProperty;
    private Model model;
    private ViewState viewState;

    public ManageExerciseViewModel(Model model,ViewState viewState){
        this.model = model;
        this.viewState = viewState;
        this.errorProperty = new SimpleStringProperty();
        this.headerProperty = new SimpleStringProperty();
        this.completedProperty = new SimpleBooleanProperty();
        this.topicProperty = new SimpleStringProperty();
        this.numberProperty = new SimpleIntegerProperty();
        this.sessionProperty = new SimpleIntegerProperty();
        this.editableProperty = new SimpleBooleanProperty();
    }

    public void reset(){
        this.errorProperty = null;
        this.headerProperty = null;
        this.completedProperty = null;
        this.topicProperty = null;
        this.numberProperty = null;
        this.sessionProperty = null;
        this.editableProperty = null;

    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    public StringProperty getHeaderProperty() {
        return headerProperty;
    }

    public BooleanProperty getCompletedProperty(){
        return completedProperty;
    }

    public StringProperty getTopicProperty() {
        return topicProperty;
    }

    public IntegerProperty getNumberProperty() {
        return numberProperty;
    }

    public IntegerProperty getSessionProperty() {
        return sessionProperty;
    }

    public BooleanProperty getEditableProperty() {
        return editableProperty;
    }

    private Exercise createExerciseObject(){
        Exercise exercise = new Exercise(getSessionProperty().get(),getNumberProperty().get(),getTopicProperty().get());
        exercise.setCompleted(completedProperty.get());
        return exercise;
    }

    public boolean accept(){
        try {
            if (headerProperty.get().contains("Add")) {
                model.addExercise(createExerciseObject());
            } else if (headerProperty.get().contains("Edit")) {
                model.editExercise(viewState.getNumber(), createExerciseObject());
            } else if (headerProperty.get().contains("Remove")) {
                model.removeExercise(viewState.getNumber());
            }
        }
        catch (Exception e){
            errorProperty.set(e.getMessage());
            return false;
        }
        return true;
    }
}
