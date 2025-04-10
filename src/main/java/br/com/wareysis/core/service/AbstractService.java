package br.com.wareysis.core.service;

import br.com.wareysis.core.service.messages.MessageService;

import jakarta.inject.Inject;

public abstract class AbstractService {

    @Inject
    public MessageService messageService;

}
