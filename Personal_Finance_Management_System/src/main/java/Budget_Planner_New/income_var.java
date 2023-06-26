package Budget_Planner_New;

public class income_var {

    String catogery;
    int actual;

    public income_var(String catogery, int actual) {
        this.actual = actual;
        this.catogery = catogery;
    }

    public String getCatogery() {
        return catogery;
    }

    public void setCatogery(String catogery) {
        this.catogery = catogery;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

}
