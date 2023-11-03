# RetroGameEngine
Игровой движок для создания небольших игр на JavaFX.

Интересно, помнит ли ещё кто времена простеньких игр на java? А вот я хорошо помню, как 15 лет назад играл на своём кнопочном телефоне в змейку. Это была моя первая игра, которая мне до сих пор очень нравится. И вот, настало время мне сделать свою змейку со своими правилами, за одно создав для неё небольшой движок. Ну как движок, я бы скорее сказал набор вспомогательных методов которые очень облегчат рутину по созданию подобных небольших игр, а так же оставят вам красивый код, в котором не будет ничего лишнего кроме очень простой и элементарно понятной логики. Этот движок отлично подойдёт тем, кто хочет сделать свою игру не углубляясь в изучение JavaFX, а так же не заморачиваясь с его проблемами, которых, к слову, более чем хватает.

# Инструкция
Для начала скажу, что ваш класс должен быть унаследован от движка, а так же необходимо проделать некоторые махинации внутри движка и вашего класса, чтобы всё корректно работало. для примера посмотрите на классы SnakeGame и config.RetroGameEngine.

1. protected void initialize() - наш метод инициализации, или же наша отправная точка. (переопределяем)
2. public void createGameField(int rowCount, int columnCount, int cellWidth, int cellHeight, Color color). Этот метод создаёт нам игровое поле, в котором мы можем изменять буквально всё. колличество столбцов, размеры ячеек, а так же их цвет.
3. public void animation(int i, boolean startAnimation) - метод запускающий наш цикл по обновлению кадров в игре. Вам не придётся заморачиваться с циклами и потоками. Вызывайте метод с параметром true для запуска цикла, false - для его остановки. параметр int - скорость в наносекундах.
4. public void onFrame () - метод, позволяющий ложить внутрь цикла вашу логику которая должна исполнятся каждую итерацию (переопределяем).
5. public void changeCell(int x, int y, String string) - используем чтобы поместить в указаную ячейку какой-то текст. Например цифры для игры в сапёра или 2048, или можем поместить смайлик.
6. public void onClick (KeyCode keyCode) {} - ваша логика при нажатии клавиш (переопределяем).
7. public void changeCellColor(int x, int y, Color color) - изменение цвета ячейки.
8. public void showGrid (boolean show) - включает/выключает видимость границ ячеек

   И на этом пока что всё. Планируется развивать движок, добавлять новые функции и улучшать старые. Параллельно займусь созданием игр под этот движок, может даже придумаю какую-нибудь свою игру
