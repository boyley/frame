package com.bogle.frame.weixin.persistence;


import com.bogle.frame.weixin.defines.TicketType;
import com.bogle.frame.weixin.domain.Ticket;

public interface TicketMapper {

    int insertSelective(Ticket record);

    Ticket selectLastTicket(TicketType ticketType);
}