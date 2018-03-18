package oop.hw2;

public class PixelToASCIIChar {
	/*
	Возможно стоит избавиться от класса и перенести функцию в немного измененный ConvertHelper?
	Чтобы вся функция конвертации лежала на нем
	Непонятно, что за значение переводится в char
	*/
    public static char toChar(int index) {
        char selection;

        if (index >= 230.0) {
            selection = ' ';
        } else if (index >= 200.0) {
            selection = '.';
        } else if (index >= 180.0) {
            selection = '*';
        } else if (index >= 160.0) {
            selection = ':';
        } else if (index >= 130.0) {
            selection = 'o';
        } else if (index >= 100.0) {
            selection = '&';
        } else if (index >= 70.0) {
            selection = '8';
        } else if (index >= 50.0) {
            selection = '#';
        } else {
            selection = '@';
        }
        return selection;
    }
}
