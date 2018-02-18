package dist.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="ProcessList")
@Component
public class ProcessList {

	List<Process> processList;
	
	public List<Process> getProcessList(){
		return processList;
	}
	
	public void setProcessList(List<Process> processList) {
		this.processList=processList;
	}
}
