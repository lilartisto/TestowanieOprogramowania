import org.junit.jupiter.api.BeforeEach;

public class StudentRepositoryTest {

    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp(){
        studentRepository = new StudentRepository();
    }

    // Artur:
    // dodanie
    //      takiego samego id
    //      null'a
    //      pola sa pustymi stringami
    //      indeks taki sam jak inny
    //  usuniecie
    //      null'a
    //  uaktualnienie
    //      poprawne
    //      ktores z pol jest nullem
    //      pola sa pustymi stringami
    //      indeks taki sam jak inny

    // Kacper:
    // dodanie
    //      poprawne
    //      ktores z pol studenta jest nullem
    //      indeks/semestr ujemny
    //
    //  usuniecie
    //      poprawne
    //      nieistniejacego studenta
    //
    //  uaktualnienie
    //      null'a
    //      na te same wartosci
    //      indeks/semestr ujemny
    //
    //  czytanie
    //      wczytaj nieistniejacego studenta

}
