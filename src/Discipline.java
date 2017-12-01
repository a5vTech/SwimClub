public class Discipline {
    private String discipline;
    private String[] disciplines = {"butterfly","crawl","rygsvømning","brystsvømning","hundesvømnming"};


//Constructors
    public Discipline(int option){
        switch(option){
            case 1:
                discipline = disciplines[0];
            break;
            
            case 2:
                discipline = disciplines[1];
            break;
            
            case 3:
                discipline = disciplines[2];
            break;
            
            case 4:
                discipline = disciplines[3];
            break;
            
            case 5:
                discipline = disciplines[4];
            break;
            
            default:
                discipline = disciplines[1];
            break;
        }    
    }
    public Discipline(String option){
        if(option.equalsIgnoreCase(disciplines[0])){
            discipline = disciplines[0]; //Butterfly
        } else if(option.equalsIgnoreCase(disciplines[1])){
            discipline = disciplines[1]; //Crawl
        }else if(option.equalsIgnoreCase(disciplines[2])){
            discipline = disciplines[2]; //Backstroke
        } else if(option.equalsIgnoreCase(disciplines[3])){
            discipline = disciplines[3]; //Breaststroke
        }else if(option.equalsIgnoreCase(disciplines[4])){
            discipline = disciplines[4]; //Dogpaddle
        }else{
            discipline = disciplines [1]; //Default crawl
        }
    }



//Getters
    public String getDiscipline(){
        return discipline;
    }


// Setters
    public void setDiscipline(String discipline){
        this.discipline = discipline;
    }
    
    public String toString(){
        return discipline;
    }
}
