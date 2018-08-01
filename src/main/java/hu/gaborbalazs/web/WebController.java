package hu.gaborbalazs.web;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.gaborbalazs.model.Greeting;

@RestController
public class WebController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/greetingCorsOriginAll")
	public Greeting greetingCorsOriginAll(@RequestParam(required = false, defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@CrossOrigin(origins = "http://localhost:9000")
	@GetMapping("/greetingCorsOrigin9000")
	public Greeting greetingCorsOrigin9000(@RequestParam(required = false, defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@CrossOrigin(origins = "http://localhost:9001")
	@GetMapping("/greetingCorsOrigin9001")
	public Greeting greetingCorsOrigin9001(@RequestParam(required = false, defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
