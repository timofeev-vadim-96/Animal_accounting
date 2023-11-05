package validator;

import util.Command;
import util.YESNO;

import java.util.Arrays;
import java.util.List;

public class Validator {
    private static final String CYRILLIC_REGEX = "[а-яёА-ЯЁ]+";
    private static final String ENG_REGEX = "[a-zA-Z]+";
    private static final String DATA_REGEX = "(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[0-2])[.](19[0-9][0-9]|20[0-9][0-9])";
    private static final String NUMBER_REGEX = "(\\d*\\.)?\\d+";

    public boolean commandValidate(String command) {
        return Arrays.stream(Command.class.getEnumConstants()).anyMatch(e -> e.name().equals(command));
    }

    public boolean stringValidate(String name) {
        return name.matches(CYRILLIC_REGEX) | name.matches(ENG_REGEX);
    }

    public boolean dateValidate(String date) {
        return date.matches(DATA_REGEX);
    }

    public boolean numbValidate(String numb) {
        return numb.matches(NUMBER_REGEX);
    }

    public boolean answerValidate(String answer) {//yes or no
        return Arrays.stream(YESNO.class.getEnumConstants()).anyMatch(e -> e.name().equals(answer));
    }

    public boolean checkListLength(List<String> list, int expectedLength) {
        return expectedLength == list.size();
    }
}
