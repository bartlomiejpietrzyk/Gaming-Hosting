package pl.bartlomiejpietrzyk.errors;


//@ControllerAdvice
public class ErrorsController {//implements ErrorController {

//
//    @RequestMapping("/error")
//    public ModelAndView handleError(HttpServletResponse response) {
//        ModelAndView modelAndView = new ModelAndView();
//
//        if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
//            modelAndView.setViewName("/404");
//        } else if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
//            modelAndView.setViewName("/403");
//        } else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//            modelAndView.setViewName("/500");
//        } else {
//            modelAndView.setViewName("error");
//        }
//
//        return modelAndView;
//    }
//
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}