module br.ufrpe.passagensvendas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens br.ufrpe.passagensvendas to javafx.fxml;
    exports br.ufrpe.passagensvendas;
}