package viewmodel;

public class ViewState {
    private String number;
    private boolean remove;

    public ViewState(){
        number = "";
        remove = false;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void removeNumber(){
        number = "";
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public void blalala()
    {
        System.out.println("Nothing");
    }
}
