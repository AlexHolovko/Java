package lab3;

class MusicalInstrument {
    protected String name;
    protected String description;
    protected String history;

    public MusicalInstrument(String name, String description, String history) {
        this.name = name;
        this.description = description;
        this.history = history;
    }

    public void sound() {
        System.out.println("Sound of " + name);
    }

    public void show() {
        System.out.println("Instrument: " + name);
    }

    public void desc() {
        System.out.println("Description: " + description);
    }

    public void history() {
        System.out.println("History: " + history);
    }
}

class Violin extends MusicalInstrument {
    public Violin() {
        super("Violin",
              "A string instrument played with a bow, known for its expressive sound.",
              "The violin originated in 16th century Italy and evolved from earlier string instruments.");
    }

    @Override
    public void sound() {
        System.out.println("Violin plays a melodious and expressive sound.");
    }
}

class Trombone extends MusicalInstrument {
    public Trombone() {
        super("Trombone",
              "A brass instrument with a telescoping slide used to change pitch.",
              "The trombone dates back to the 15th century and was used in orchestras and bands.");
    }

    @Override
    public void sound() {
        System.out.println("Trombone produces a rich, brassy tone.");
    }
}

class Ukulele extends MusicalInstrument {
    public Ukulele() {
        super("Ukulele",
              "A small, guitar-like instrument associated with Hawaiian music.",
              "The ukulele was developed in the 19th century based on several small guitar-like instruments from Portugal.");
    }

    @Override
    public void sound() {
        System.out.println("Ukulele sounds bright and cheerful.");
    }
}

class Cello extends MusicalInstrument {
    public Cello() {
        super("Cello",
              "A large string instrument with a deep, warm tone.",
              "The cello was developed in the early 16th century and is a key instrument in orchestras.");
    }

    @Override
    public void sound() {
        System.out.println("Cello produces a rich and deep sound.");
    }
}

public class task4 {
    public static void main(String[] args) {
        MusicalInstrument[] instruments = {
            new Violin(),
            new Trombone(),
            new Ukulele(),
            new Cello()
        };

        for (MusicalInstrument instr : instruments) {
            instr.show();
            instr.sound();
            instr.desc();
            instr.history();
            System.out.println();
        }
    }
}
