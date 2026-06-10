package it.uniroma3.diadia;
import java.util.List;
import java.util.ArrayList;

public class IOSimulator implements IO{
	private List<String> input;
	private List<String> output;
	
	public IOSimulator(List<String> input) {
		this.input=new ArrayList<>(input);
		this.output = new ArrayList<>();
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		output.add(msg);
	}
	
	@Override
	public String leggiRiga() {
		if(!input.isEmpty())
			return input.remove(0);
		return null;
	}
	
	public List<String> getOutput(){
		return this.output;
	}
	
	public int getNumeroOutput() {
		return this.output.size();
	}
}
