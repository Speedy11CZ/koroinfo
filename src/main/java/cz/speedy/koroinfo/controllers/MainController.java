package cz.speedy.koroinfo.controllers;

import cz.speedy.koroinfo.data.entities.News;
import cz.speedy.koroinfo.data.entities.Page;
import cz.speedy.koroinfo.data.repositories.ChocoAlertRepository;
import cz.speedy.koroinfo.data.repositories.NewsRepository;
import cz.speedy.koroinfo.data.repositories.PageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class MainController {

    private final PageRepository pageRepository;
    private final NewsRepository newsRepository;
    private final ChocoAlertRepository chocoAlertRepository;

    public MainController(PageRepository pageRepository, NewsRepository newsRepository, ChocoAlertRepository chocoAlertRepository) {
        this.pageRepository = pageRepository;
        this.newsRepository = newsRepository;
        this.chocoAlertRepository = chocoAlertRepository;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("allNews", newsRepository.findAll());
        modelAndView.addObject("chocoAlert", chocoAlertRepository.findTopByOrderByIdDesc());
        return modelAndView;
    }

    @GetMapping("/page/{endpoint}")
    public ModelAndView index(@PathVariable String endpoint) {
        ModelAndView modelAndView = new ModelAndView("page");
        Page page = pageRepository.findByEndpoint(endpoint);
        if(page != null) {
            modelAndView.addObject("pageTitle", page.getTitle());
            modelAndView.addObject("content", page.getContent());
            return modelAndView;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/news/{id}")
    public ModelAndView news(@PathVariable Integer id) {
        Optional<News> news = newsRepository.findById(id);

        if(news.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("news");
            modelAndView.addObject("news", news.get());
            return modelAndView;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    /*
    @GetMapping("/sub/verification/{uuid}")
    public RedirectView verifySubscription(@PathVariable String uuid) {
        if(subscriptionService.verify(uuid)) {
            return new RedirectView("/");
        } else {
            return new RedirectView("about:blank");
        }
    }

    @RequestMapping(value = "/sub/subscribe", method = RequestMethod.POST)
    public String submit(@ModelAttribute("subscribe") SubscribeForm subscribeForm) {
        Set<ConstraintViolation<SubscribeForm>> subscribeFormValidation = validatorFactory.getValidator().validate(subscribeForm);
        if(subscribeForm.getEmail().)
        return "employeeView";
    }
     */
}
