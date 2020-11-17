package com.sat.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sat.bean.Ingredient;
import com.sat.bean.Ingredient.Type;
import com.sat.bean.Taco;

import lombok.extern.slf4j.Slf4j;

@Slf4j//Lombok提供的日志注解会自动生成logger
@Controller
@RequestMapping("/design")
public class DesignTacoController {
//	private static final Logger log = LoggerFactory.getLogger(DesignTacoController.class);
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
		  new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
		  new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
		  new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
		  new Ingredient("CARN", "Carnitas", Type.PROTEIN),
		  new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
		  new Ingredient("LETC", "Lettuce", Type.VEGGIES),
		  new Ingredient("CHED", "Cheddar", Type.CHEESE),
		  new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
		  new Ingredient("SLSA", "Salsa", Type.SAUCE),
		  new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
		  model.addAttribute(type.toString().toLowerCase(),
		      filterByType(ingredients, type));
		}
	}
	
	@GetMapping//spring4.3引入的,之前用requestMapping代替
	public String showDesignForm(Model model) {
//		List<Ingredient> ingredients = Arrays.asList(
//			new Ingredient("FLTO","Flour Tortilla",Type.WRAP),
//			new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//			new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//			new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//			new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//			new Ingredient("CHED", "Cheddar", Type.CHEESE),
//			new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//			new Ingredient("SLSA", "Salsa", Type.SAUCE),
//			new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//		);
//		Type[] types = Ingredient.Type.values();
//		for(Type type : types) {
//			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,type));
//		}
		model.addAttribute("design",new Taco());
		return "design";
	}
	
	//@Valid和Errors执行校验
	//为什么加了@ModelAttribute("design")校验错误再返回的时候就不报错了呢
	//因为ModelAttribute修饰方法参数的作用就是取出前台传给后台的值，放到model中再传给前台，必须有name不然key会为taco
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design,Errors errors) {
//	public String processDesign(@Valid @ModelAttribute("design") Taco design,Errors errors, Model model) {
//		log.info("model中design值:" + model.getAttribute("design").toString());
		
		if(errors.hasErrors()) {
			return "design";
		}
		log.info("processing design:" + design);
		return "redirect:/orders/current";
	}
	
	@ModelAttribute(name="design")
	public Taco satSetDesign() {
		Taco ta = new Taco();
		ta.setName("sat");
		ta.setIngredients(Arrays.asList("FLTO","Flour Tortilla"));
		return ta;
	}

	private Object filterByType(List<Ingredient> ingredients, Type type) {
		// TODO Auto-generated method stub
		//流式编程，将list集合中对象的Type属性等于type的筛选出来重选转为集合输出
		return ingredients.stream().filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}
}
