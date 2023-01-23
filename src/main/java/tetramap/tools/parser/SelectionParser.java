package tetramap.tools.parser;

import tetramap.entity.selection.Selection;

import java.io.File;

public interface SelectionParser {

    /**
     * Генерация модели области выделения в файл
     * @param file Файл
     * @param selection Выделенная область
     */
    void write(File file, Selection selection);

    /**
     * Парсинг модели области выделения из файла
     * @param file Файл
     */
    Selection parse(File file) throws Exception;
}
