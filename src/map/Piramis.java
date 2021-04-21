package map;


public class Piramis extends Map{

    private boolean visited;

    public Piramis() {
        super("/resources/piramis",0);
        this.visited=false;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        this.visited = true;
    }
}
