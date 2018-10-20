package calculoSalario;
import javax.swing.*;
import java.text.NumberFormat;
public class Salario {
    private Double salBruto;
    private Double salLiq;
    private Double fgts;
    private Double inss;
    private Double irpf;
    private Double dependentes;
    private int mesesTrabalhados;
    private int numDepen;

    NumberFormat nf = NumberFormat.getCurrencyInstance();

    public Salario(){
        this(0d,0d,0d,0d,0d,0d,0);
    }

    public Salario(Double salBruto, Double salLiq, Double fgts, Double inss,
                   Double irpf, Double dependentes, int mesesTrabalhados) {
        this.salBruto = salBruto;
        this.salLiq = salLiq;
        this.fgts = fgts;
        this.inss = inss;
        this.irpf = irpf;
        this.dependentes = dependentes;
        this.mesesTrabalhados = mesesTrabalhados;
    }

    public Double getSalBruto() {
        return salBruto;
    }
    public void setSalBruto(Double salBruto) {
        this.salBruto = salBruto;
    }
    public Double getSalLiq() {
        return salLiq;
    }
    public void setSalLiq(Double salLiq) {
        this.salLiq = salLiq;
    }
    public Double getFgts() {
        return fgts;
    }
    public void setFgts(Double fgts) {
        this.fgts = fgts;
    }
    public Double getInss() {
        return inss;
    }
    public void setInss(Double inss) {
        this.inss = inss;
    }
    public Double getIrpf() {
        return irpf;
    }
    public void setIrpf(Double irpf) {
        this.irpf = irpf;
    }
    public Double getDependentes() {
        return dependentes;
    }
    public void setDependentes(Double dependentes) {
        this.dependentes = dependentes;
    }
    public int getNumDepen() {
        return numDepen;
    }
    public void setNumDepen(int numDepen) {
        this.numDepen = numDepen;
    }
    public int getMesesTrabalhados() {
        return mesesTrabalhados;
    }
    public void setMesesTrabalhados(int mesesTrabalhados) {
        this.mesesTrabalhados = mesesTrabalhados;
    }

    public void cadastrar(Double salario, int numDepen){
        setSalBruto(salario);
        setNumDepen(numDepen);
    }

    private boolean faixaSalarial(Double salario, Double menorValor){
        return salario <= menorValor;
    }

    private void calcularDependentes(){
        setDependentes(getNumDepen() * 189.59);
    }

    private void calcularINSS(){
        if(faixaSalarial(getSalBruto(),1693.72))
            setInss(getSalBruto() * 8 / 100);
        else if(faixaSalarial(getSalBruto(),2822.90))
            setInss(getSalBruto() * 9 / 100);
        else
            setInss(getSalBruto() * 11 / 100);
    }

    private void calcularIRPF(){
        calcularDependentes();
        Double salarioDep = getSalLiq() - getDependentes();
        if(faixaSalarial(salarioDep, 1903.98))
            setIrpf(salarioDep * 0);
        else if(faixaSalarial(salarioDep, 2826.65))
            setIrpf((salarioDep * 7.5 / 100) - 142.80);
        else if(faixaSalarial(salarioDep, 3751.05))
            setIrpf((salarioDep * 15 / 100) - 354.80);
        else if(faixaSalarial(salarioDep,4664.68))
            setIrpf((salarioDep * 22.5 / 100) - 636.13);
        else
            setIrpf((salarioDep * 27.5 / 100) - 869.36);
    }

    public void calcularFGTS(int mesesTrabalhados){
        setMesesTrabalhados(mesesTrabalhados);
        setFgts((getSalBruto() * getMesesTrabalhados()) * 8 / 100);
    }

    public void calcularSalLiq(){
        calcularINSS();
        setSalLiq(getSalBruto() - getInss());
        calcularIRPF();
        setSalLiq(getSalLiq() - getIrpf());
    }

    public void printSal(){
        JOptionPane.showMessageDialog(null,
                "Salario Bruto........... " + nf.format(getSalBruto())
                +"\nSalario Liquido........ " + nf.format(getSalLiq())
                + "\n\nDependentes: " + getNumDepen()
                + "\nDedução da base: " + nf.format(getDependentes())
                + "\n\nDescontos:"
                + "\nINSS: " + nf.format(getInss())
                + "\nIRPF: " + nf.format(getIrpf()),
                "Folha de Pagamento", -1);
    }

    public void printFGTS(){
        JOptionPane.showMessageDialog(null,
                "\nMeses trabalhados: " + getMesesTrabalhados()
                        + "\nSalario Bruto.... " + nf.format(getSalBruto())
                        + "\nFGTS................ " + nf.format(getFgts()),
                "FGTS", -1);
    }

}
