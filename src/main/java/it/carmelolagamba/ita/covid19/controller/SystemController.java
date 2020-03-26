package it.carmelolagamba.ita.covid19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.carmelolagamba.ita.covid19.config.ApplicationProperties;
import it.carmelolagamba.ita.covid19.dto.system.InfoDto;
import it.carmelolagamba.mongo.service.MongoService;
import it.carmelolagamba.mongo.utils.MongoStatusConnection;

@RestController
@Api(value = "System Information")
public class SystemController {

	@Autowired
	private ApplicationProperties config;
	
	@Autowired
	private MongoService mongoService;

	@ApiOperation(value = "Alive test")
	@RequestMapping(method = RequestMethod.GET, path = "/ping")
	public String ping() {
		return "pong";
	}
	
	@ApiOperation(value = "Mongo status")
	@RequestMapping(method = RequestMethod.GET, path = "/mongo/status")
	public MongoStatusConnection status() {
		return mongoService.statusInfo();
	}

	@ApiOperation(value = "Get system info")
	@RequestMapping(method = RequestMethod.GET, path = "/info")
	public InfoDto info() {

		InfoDto infoDto = new InfoDto();

		infoDto.setEnvironment(config.getEnvironment());
		infoDto.setName(config.getName());
		infoDto.setUrl(config.getUrl());
		infoDto.setPort(config.getPort());

		return infoDto;
	}

}
