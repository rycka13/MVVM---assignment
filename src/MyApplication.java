import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManager;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    Model model = new ModelManager();
//    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
//    ViewHandler view = new ViewHandler(viewModelFactory);

//    view.start(primaryStage);
  }
}
