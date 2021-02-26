package viewmodel;

import com.sun.webkit.Timer;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ListExerciseViewModel implements PropertyChangeListener {
    private ObservableList<SimpleExerciseViewModel> list;
    private ObjectProperty<SimpleExerciseViewModel> selectedExerciseProperty;
    private StringProperty errorProperty;
    private Model model;
    private ViewState viewState;

    public ListExerciseViewModel(Model model, ViewState viewState){
        this.model = model;
        this.viewState = viewState;
        this.list = FXCollections.observableArrayList();
        loadFromModel();
        this.selectedExerciseProperty = new SimpleObjectProperty<>();
        this.errorProperty = new SimpleStringProperty();
        model.addListener(this);
    }

    public void clear(){
        list = null;
        selectedExerciseProperty = null;
        errorProperty = null;
    }

    private void loadFromModel(){
        list.clear();
        ArrayList<Exercise> exercises = model.getAllExercises();
        for(int i = 0; i <  exercises.size() ; i++){
            list.add(new SimpleExerciseViewModel(exercises.get(i)));
        }
    }

    public void addEdit(){
        viewState.setRemove(false);
        if(selectedExerciseProperty.get() == null){
            viewState.setNumber(null);
        }
        else viewState.setNumber(selectedExerciseProperty.get().getNumberProperty().get());
    }

    public boolean remove(){
        if(viewState.isRemove()){
            removeSimpleExercise(selectedExerciseProperty.get().getNumberProperty().get());
            return true;
        }
        return false;
    }

    private void removeSimpleExercise(String number){
        for(int i = 0; i < list.size() ; i++){
            if(list.get(i).getNumberProperty().get().equals(number)){
                list.remove(i);
                break;
            }
        }
    }

    private void addSimpleExercise(){

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            if(evt.getPropertyName().equals("Add"))
            {
                Exercise exercise = (Exercise) evt.getNewValue();
                list.add(new SimpleExerciseViewModel(exercise));

            }
            else if(evt.getPropertyName().equals("Edit"))
            {
                for(int i = 0; i < list.size() ; i++){
                    if(list.get(i).getNumberProperty().equals((Integer)evt.getOldValue())){
                        list.set(i,new SimpleExerciseViewModel((Exercise)evt.getNewValue()));
                    }
                }
            }
            else if(evt.getPropertyName().equals("Remove"))
            {
                for(int i = 0; i < list.size() ; i++){
                    if(evt.getOldValue().equals(list.get(i).getNumberProperty().get())){
                        list.remove(i);
                    }
                }
            }
        });
    }

}
