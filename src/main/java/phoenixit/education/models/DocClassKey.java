package phoenixit.education.models;


import lombok.Getter;

public enum DocClassKey {

    Rock("Rock"),
    Metal("Metal");

    DocClassKey(String type) {
        this.type = type;
    }

    @Getter private String type;

    public static DocClassKey convertToEnum(String line) {
        DocClassKey result = null;
        for (DocClassKey type : DocClassKey.values()) {
            if (type.getType().equals(line)) {
                result = type;
                break;
            }
        }
        return result;
    }
}
