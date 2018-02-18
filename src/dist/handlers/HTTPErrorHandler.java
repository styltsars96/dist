package dist.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTTPErrorHandler {

	@RequestMapping(value = "/400")
	public String error400(Model model) {
		model.addAttribute("errorMsg", "400 Bad Request!");
		return "error";
	}

	@RequestMapping(value = "/404")
	public String error404(Model model) {
		model.addAttribute("errorMsg", "404 Not Found!");
		return "error";
	}
	@RequestMapping(value = "/403")
	public String error403(Model model) {
		model.addAttribute("errorMsg", "403 Access Denied!");
		return "error";
	}

	@RequestMapping(value = "/500")
	public String error500(Model model) {
		model.addAttribute("errorMsg", "500 Internal Server Error");
		return "error";
	}
}