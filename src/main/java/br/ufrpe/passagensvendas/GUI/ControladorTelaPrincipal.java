package br.ufrpe.passagensvendas.GUI;

import br.ufrpe.passagensvendas.negocio.Fachada;
import br.ufrpe.passagensvendas.negocio.beans.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.time.LocalDateTime;

public class ControladorTelaPrincipal {

    private final Fachada fachada = Fachada.getInstance();

    @FXML
    private Spinner<Integer> spinnerVooHora;
    @FXML
    private Spinner<Integer> spinnerVooMinuto;
    @FXML
    private TextField txtVooOrigem, txtVooDestino, txtVooPreco;
    @FXML
    private DatePicker dateVooPartida;
    @FXML
    private Spinner<Integer> spinnerVooAssentos;
    @FXML
    private ToggleButton toggleNacional;
    @FXML
    private TableView<Voo> tabelaVoos;
    @FXML
    private TableColumn<Voo, Integer> colVooId;
    @FXML
    private TableColumn<Voo, String> colVooOrigem, colVooDestino;
    @FXML
    private TableColumn<Voo, LocalDateTime> colVooPartida;
    @FXML
    private TableColumn<Voo, Integer> colVooAssentos;
    @FXML
    private Label lblVooStatus;
    @FXML
    private TextField txtPassageiroNome, txtPassageiroCpf, txtPassageiroPassaporte;
    @FXML
    private TableView<Passageiro> tabelaPassageiros;
    @FXML
    private TableColumn<Passageiro, String> colPassageiroNome, colPassageiroCpf, colPassageiroPassaporte;
    @FXML
    private Label lblPassageiroStatus;
    @FXML
    private ComboBox<Passageiro> comboPassageiro;
    @FXML
    private ComboBox<Voo> comboVoo;
    @FXML
    private Label lblCompraStatus;

    @FXML
    public void initialize() {
        configurarComponentes();
        configurarTabelas();
        carregarDadosIniciais();
        popularComboBoxes();
    }

    private void configurarComponentes() {
        spinnerVooHora.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        spinnerVooMinuto.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        Callback<ListView<Passageiro>, ListCell<Passageiro>> passageiroCellFactory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Passageiro passageiro, boolean empty) {
                super.updateItem(passageiro, empty);
                setText(empty ? "" : passageiro.getNome() + " (CPF: " + passageiro.getCpf() + ")");
            }
        };
        comboPassageiro.setCellFactory(passageiroCellFactory);
        comboPassageiro.setButtonCell(passageiroCellFactory.call(null));
        Callback<ListView<Voo>, ListCell<Voo>> vooCellFactory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Voo voo, boolean empty) {
                super.updateItem(voo, empty);
                setText(empty ? "" : "Voo " + voo.getIdVoo() + ": " + voo.getOrigem() + " -> " + voo.getDestino());
            }
        };
        comboVoo.setCellFactory(vooCellFactory);
        comboVoo.setButtonCell(vooCellFactory.call(null));
    }

    private void configurarTabelas() {
        colVooId.setCellValueFactory(new PropertyValueFactory<>("idVoo"));
        colVooOrigem.setCellValueFactory(new PropertyValueFactory<>("origem"));
        colVooDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        colVooPartida.setCellValueFactory(new PropertyValueFactory<>("dataHoraPartida"));
        colVooAssentos.setCellValueFactory(new PropertyValueFactory<>("assentosDisponiveis"));
        colPassageiroNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPassageiroCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colPassageiroPassaporte.setCellValueFactory(new PropertyValueFactory<>("passaporte"));
    }

    @FXML
    void handleCadastrarVoo(ActionEvent event) {
        try {
            LocalDateTime partida = dateVooPartida.getValue().atTime(
                    spinnerVooHora.getValue(),
                    spinnerVooMinuto.getValue()
            );
            String origem = txtVooOrigem.getText();
            String destino = txtVooDestino.getText();
            int assentos = spinnerVooAssentos.getValue();
            double preco = Double.parseDouble(txtVooPreco.getText());
            Voo novoVoo;
            if (toggleNacional.isSelected()) {
                novoVoo = new VooNacional(origem, destino, partida, assentos, preco);
            } else {
                novoVoo = new VooInternacional(origem, destino, partida, assentos, preco);
            }
            fachada.cadastrarVoo(novoVoo);
            definirStatusLabel(lblVooStatus, "Voo cadastrado com sucesso!", Color.GREEN);
            atualizarTabelaVoos();
            limparCamposVoo();
        } catch (Exception e) {
            definirStatusLabel(lblVooStatus, "Erro: " + e.getMessage(), Color.RED);
        }
    }

    private void carregarDadosIniciais() {
        if (fachada.listarVoos().isEmpty()) {
            fachada.cadastrarVoo(new VooNacional("Recife (REC)", "São Paulo (GRU)", LocalDateTime.now().plusDays(10), 150, 450.0));
            fachada.cadastrarVoo(new VooInternacional("São Paulo (GRU)", "Lisboa (LIS)", LocalDateTime.now().plusDays(25), 200, 3200.0));
        }
        if (fachada.listarPassageiros().isEmpty()) {
            try {
                fachada.cadastrarPassageiro(new Passageiro("Maria Souza", "11122233344", "BR12345"));
                fachada.cadastrarPassageiro(new Passageiro("João Pereira", "55566677788", null));
            } catch (Exception e) {
            }
        }
        atualizarTabelaVoos();
        atualizarTabelaPassageiros();
    }

    private void popularComboBoxes() {
        comboPassageiro.setItems(FXCollections.observableArrayList(fachada.listarPassageiros()));
        comboVoo.setItems(FXCollections.observableArrayList(fachada.listarVoos()));
    }

    @FXML
    void handleCadastrarPassageiro(ActionEvent event) {
        try {
            String nome = txtPassageiroNome.getText();
            String cpf = txtPassageiroCpf.getText();
            String passaporte = txtPassageiroPassaporte.getText();
            Passageiro novoPassageiro = new Passageiro(nome, cpf, passaporte);
            fachada.cadastrarPassageiro(novoPassageiro);
            definirStatusLabel(lblPassageiroStatus, "Passageiro cadastrado!", Color.GREEN);
            atualizarTabelaPassageiros();
            limparCamposPassageiro();
        } catch (Exception e) {
            definirStatusLabel(lblPassageiroStatus, "Erro: " + e.getMessage(), Color.RED);
        }
    }

    private void atualizarTabelaVoos() {
        tabelaVoos.setItems(FXCollections.observableArrayList(fachada.listarVoos()));
        comboVoo.setItems(FXCollections.observableArrayList(fachada.listarVoos()));
    }

    private void atualizarTabelaPassageiros() {
        tabelaPassageiros.setItems(FXCollections.observableArrayList(fachada.listarPassageiros()));
        comboPassageiro.setItems(FXCollections.observableArrayList(fachada.listarPassageiros()));
    }

    private void limparCamposVoo() {
        txtVooOrigem.clear();
        txtVooDestino.clear();
        dateVooPartida.setValue(null);
        txtVooPreco.clear();
        spinnerVooAssentos.getValueFactory().setValue(150);
        spinnerVooHora.getValueFactory().setValue(12);
        spinnerVooMinuto.getValueFactory().setValue(0);
    }

    private void limparCamposPassageiro() {
        txtPassageiroNome.clear();
        txtPassageiroCpf.clear();
        txtPassageiroPassaporte.clear();
    }

    private void definirStatusLabel(Label label, String texto, Color cor) {
        label.setText(texto);
        label.setTextFill(cor);
    }
}