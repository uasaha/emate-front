package me.emate.matefront.visitor.service;

public interface VisitorService {

  Integer getTodayVisitor();

  Integer getTotalVisitor();

  void setTodayVisitorToTotal();
}
