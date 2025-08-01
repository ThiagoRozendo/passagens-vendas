package br.ufrpe.passagensvendas.GUI;

import br.ufrpe.passagensvendas.negocio.Fachada;
import br.ufrpe.passagensvendas.negocio.beans.Voo;
import br.ufrpe.passagensvendas.negocio.beans.VooInternacional;
import br.ufrpe.passagensvendas.negocio.beans.VooNacional;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

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
    public void initialize() {
        configurarComponentes();
        configurarTabelas();
        carregarDadosIniciais();
    }

    private void configurarComponentes() {
        spinnerVooHora.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        spinnerVooMinuto.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
    }

    private void configurarTabelas() {
        colVooId.setCellValueFactory(new PropertyValueFactory<>("idVoo"));
        colVooOrigem.setCellValueFactory(new PropertyValueFactory<>("origem"));
        colVooDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        colVooPartida.setCellValueFactory(new PropertyValueFactory<>("dataHoraPartida"));
        colVooAssentos.setCellValueFactory(new PropertyValueFactory<>("assentosDisponiveis"));
    }

    private void carregarDadosIniciais() {
        if (fachada.listarVoos().isEmpty()) {
            fachada.cadastrarVoo(new VooNacional("Recife (REC)", "São Paulo (GRU)", LocalDateTime.now().plusDays(10), 150, 450.0));
            fachada.cadastrarVoo(new VooInternacional("São Paulo (GRU)", "Lisboa (LIS)", LocalDateTime.now().plusDays(25), 200, 3200.0));
        }
        atualizarTabelaVoos();
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

    private void atualizarTabelaVoos() {
        tabelaVoos.setItems(FXCollections.observableArrayList(fachada.listarVoos()));
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

    private void definirStatusLabel(Label label, String texto, Color cor) {
        label.setText(texto);
        label.setTextFill(cor);
    }
}