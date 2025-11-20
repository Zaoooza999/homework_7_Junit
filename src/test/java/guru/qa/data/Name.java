package guru.qa.data;

public enum Name {
    Max("Maximov"),
    Ivan("Ivanov");

    public final String lastName;

    Name(String description) {
        this.lastName = description;
    }
}
