package tetramap.event;

public interface DrawShapeEndEventListener {

    /**
     * Метод, вызывающийся после окончания рисования фигуры
     * @param shape название фигуры
     */
    void drawEnd(String shape);
}
