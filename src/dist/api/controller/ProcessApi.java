package dist.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import dist.entities.ProcessList;
import dist.service.MainService;
import dist.entities.Process;

@RestController
@RequestMapping("/api/process")
public class ProcessApi {
	@Autowired
    private MainService mainService;
	
	@Autowired 
	private ProcessList processList;
	
	@GetMapping(value = "/list/{customerAfm}",  produces =  "application/json")
	public ProcessList getProcessByCustomer(@PathVariable("customerAfm") String customerAfm) {
		
		List<Process> processes = mainService.getProcessesByCustomer(customerAfm);
        System.out.println("processes :" + processes);
        this.processList.setProcessList(processes);
		return processList;
	}
	
	@RequestMapping(value = "/{plate}", method = RequestMethod.GET, produces = "application/json")
    public Process getProcess(@PathVariable("plate") String plate) {
            Process process = mainService.getProcessByPlate(plate);
            System.out.println("APP SERVER: Process :" + process);
            return process;
    }
	
}
