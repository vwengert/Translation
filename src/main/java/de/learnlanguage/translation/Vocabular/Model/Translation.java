package de.learnlanguage.translation.Vocabular.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Translation {
    @Id
    @SequenceGenerator(
            name = "translation_sequence",
            sequenceName = "translation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "translation_sequence"
    )
    private Long id;
    private String word;
    private String translation;

    public Translation(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Translation))
            return false;
        Translation that = (Translation) obj;
        return this.word.equals(that.word) && this.translation.equals(that.translation);
    }
}
