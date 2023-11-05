package view;

import presenter.Presenter;
import util.AnimalFabric;
import util.Command;
import util.YESNO;
import validator.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class View {
    private Scanner scanner;
    private Validator validator;
    private Presenter presenter;
    private AnimalFabric animalFabric;

    public View() {
        this.validator = new Validator();
        this.presenter = new Presenter();
        this.animalFabric = new AnimalFabric();
    }

    public void menu() {
        presenter.run();
        Command com;
        instruction();
        while (true) {
            String command = prompt("Введите команду: ADD/TEACH/COMMANDS/SHOW/REMOVE/COUNTER/SAVE/EXIT").toUpperCase();
            boolean isCorrectCommandValidator = validator.commandValidate(command);
            if (!isCorrectCommandValidator) {
                System.out.println("Введенная команда не корректна!");
                continue;
            }
            com = Command.valueOf(command);
            switch (com) {
                case ADD:
                    System.out.println("Выберите вид нового животного (введите соответствующую цифру) : \n" +
                            "1. Cat; 2. Dog, 3. Hamster, 4. Horse, 5. Camel, 6. Donkey");
                    int choiceInt = getCorrectNumb();
                    if (choiceInt < 1 | choiceInt > 6) {
                        System.out.printf("Введенное число больше количества типов животных в реестре " +
                                "(всего: %s)\n", 6);
                    } else {
                        presenter.addNewAnimal(animalFabric.createNewAnimal(choiceInt, getNewAnimal()));
                        System.out.println("Добавление произведено успешно.");
                    }
                    continue;
                case TEACH:
                    int index = checkAnimalIndex();
                    String newCommand = prompt("Введите новую команду для выбранного животного: ");
                    presenter.teachAnimalNewCommand(newCommand, index);
                    System.out.printf("Новая команда \"%s\" успешно добавлена!\n", newCommand);
                    continue;
                case COMMANDS:
                    index = checkAnimalIndex();
                    if (presenter.getCommands(index).isEmpty()) System.out.println("Список команд пуст...");
                    else System.out.println(String.join(", ", presenter.getCommands(index)));
                    continue;
                case SHOW:
                    presenter.showAnimals();
                    continue;
                case REMOVE:
                    index = checkAnimalIndex();
                    presenter.remove(index);
                    System.out.println("Удаление произведено успешно.");
                    continue;
                case COUNTER:
                    System.out.printf("Общий счетчик заведенных животных: %s.\n", presenter.getCounter());
                    continue;
                case SAVE:
                    if (presenter.compareData()) System.out.println("Несохранных изменений нет.");
                    else {
                        presenter.saveChanges();
                        System.out.println("Изменения успешно сохранены.");
                    }
                    continue;
                case EXIT:
                    exit();
                    return;
            }
        }
    }

    private void instruction() {
        System.out.println("*****Симулятор базы данных учета \"Друзей человека\" - животных******\n" +
                "ИНСТРУКЦИЯ. Чтобы продолжить работу, введите одну из нижеперечисленых команд:\n" +
                "ADD - завести новое животное\n" +
                "TEACH - научить животное новой команде\n" +
                "COMMANDS - показать список команд, которые знает животное\n" +
                "SHOW - вывести реестр имеющихся животных\n" +
                "REMOVE - удалить сведения о животном\n" +
                "SAVE - для сохранения внесенных изменений\n" +
                "EXIT - для выхода");
    }

    /**
     * Метод для получения стринговых значений с приглашением
     */
    private String prompt(String message) {
        scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    /**
     * Получение и парсинг с проверкой на валидность параметров животного
     */
    private List<String> getNewAnimal() {
        String newAnimal = prompt("Чтобы добавить новое животное в реестр, введите через пробел: \n" +
                "Имя и дату рождения в формате дд.мм.гггг. \n" +
                "Пример: Пушистик, 01.11.2023");
        List<String> animal = Arrays.asList(newAnimal.split(" "));
        boolean correctLength = validator.checkListLength(animal, 2);
        boolean correctName;
        boolean correctDate;
        boolean flag = true;
        if (!correctLength) {
            flag = false;
            System.out.println("Количество аргументов отлично от ожидаемого...");
        } else {
            correctName = validator.stringValidate(animal.get(0));
            if (!correctName) {
                flag = false;
                System.out.println("Имена не могут содержать символов, кроме букв ру/eng языков...");
            }
            correctDate = validator.dateValidate(animal.get(1));
            if (!correctDate) {
                flag = false;
                System.out.println("Формат даты не корректен...");
            }
        }
        while (!flag) {
            System.out.println("Данные введены не корректно... Повторите попытку.");
            newAnimal = prompt("Чтобы добавить новое животное в реестр, введите через пробел: \n" +
                    "Имя и дату рождения в формате дд.мм.гггг. \n" +
                    "Пример: Пушистик, 01.11.2023");
            animal = Arrays.asList(newAnimal.split(" "));
            correctLength = validator.checkListLength(animal, 2);
            if (!correctLength) {
                System.out.println("Количество аргументов отлично от ожидаемого...");
                continue;
            } else {
                correctName = validator.stringValidate(animal.get(0));
                if (!correctName) {
                    System.out.println("Имена не могут содержать символов, кроме букв ру/eng языков...");
                    continue;
                }
                correctDate = validator.dateValidate(animal.get(1));
                if (!correctDate) {
                    System.out.println("Формат даты не корректен...");
                    continue;
                }
            }
            flag = true;
        }
        return animal;
    }

    /**
     * Получение и проверка на существование полученного индекса
     */
    private int checkAnimalIndex() {
        int indexInt = getAnimalIndex();
        while (indexInt >= presenter.animalsQuantity()) {
            System.out.println(
                    "Введенное значение индекса не соответствует ни одному " +
                            "из имеющихся животных в реестре...");
            indexInt = getAnimalIndex();
        }
        return indexInt;
    }

    /**
     * Получение и проверка введенного индекса на валидность
     */
    private int getAnimalIndex() {
        String index = prompt("Введите индекс животного: ");
        boolean id = validator.numbValidate(index);
        while (!id) {
            index = prompt("Индекс может быть только числом. Введите корректный индекс: ");
            id = validator.numbValidate(index);
        }
        return Integer.parseInt(index);
    }

    /**
     * Получение и проверка числа на валидность
     */
    private int getCorrectNumb() {
        String index = prompt("Введите число: ");
        boolean id = validator.numbValidate(index);
        while (!id) {
            index = prompt("Число может содержать только цифры. Введите корректное число: ");
            id = validator.numbValidate(index);
        }
        return Integer.parseInt(index);
    }

    /**
     * Выход с проверкой на сохранение внесенных изменений
     */
    private void exit() {
        if (!presenter.compareData()) {
            String answer = prompt
                    ("У вас есть несохраненные изменения. Желаете их сохранить? (yes/no)").toUpperCase();
            boolean ans = validator.answerValidate(answer);
            while (!ans) {
                answer = prompt("Введите корректный ответ: (yes/no)");
                ans = validator.answerValidate(answer);
            }
            if (YESNO.YES.toString().equals(answer) | YESNO.Y.toString().equals(answer)) {
                presenter.saveChanges();
                System.out.println("Изменения успешно сохранены.");
            }
        }
        System.out.println("Программа завершает свою работу.");
    }
}

