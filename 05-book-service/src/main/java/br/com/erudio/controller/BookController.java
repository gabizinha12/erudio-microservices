package br.com.erudio.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.model.Book;
import br.com.erudio.proxy.CambioProxy;
import br.com.erudio.repository.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {

	@Autowired
	private Environment environment;
	@Autowired
	private BookRepository repository;
	@Autowired
	private CambioProxy proxy;

	@GetMapping(value = "/{id}/{currency}")
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
		Book book = repository.getReferenceById(id);
		if (book == null)
			throw new RuntimeException("Book not found");
		HashMap<String, String> params = new HashMap<>();
		params.put("amount", book.getPrice().toString());
		params.put("from", "USD");
		params.put("to", currency);
		var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		var port = environment.getProperty("local.server.port");
		book.setEnvironment(" Book port:  "  + port +  "  Cambio port " + cambio.getEnvironment());
		book.setPrice(cambio.getConvertedValue().doubleValue());
		return book;
	}

}
