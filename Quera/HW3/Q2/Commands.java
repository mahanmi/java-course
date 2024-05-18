public enum Commands {
        ADD_STUDIO(
                        "^add-studio#(?<username>.+)\\|(?<password>.+)\\|(?<studioID>.+)\\|(?<studioName>.+)\\|(?<year>.+)\\|(?<capacity>.+)\\|(?<address>.+)$"),
        ADD_CATEGORY(
                        "^add-category#(?<username>.+)\\|(?<password>.+)\\|(?<categoryID>.+)\\|(?<categoryName>.+)\\|(?<superCategory>.+)$"),
        ADD_CUSTOMER(
                        "^add-costumer#(?<username>.+)\\|(?<password>.+)\\|(?<subscriptionNumber>.+)\\|(?<cPassword>.+)\\|(?<name>.+)\\|(?<surname>.+)\\|(?<IDnumber>.+)\\|(?<DateOfBirth>.+)\\|(?<address>.+)$"),
        ADD_STAFF(
                        "^add-staff#(?<username>.+)\\|(?<password>.+)\\|(?<subscriptionNumber>.+)\\|(?<pPassword>.+)\\|(?<name>.+)\\|(?<surname>.+)\\|(?<IDnumber>.+)\\|(?<DateOfBirth>.+)\\|(?<address>.+)\\|(?<role>staff|professor)$"),
        ADD_MANAGER(
                        "^add-manager#(?<username>.+)\\|(?<password>.+)\\|(?<subscriptionNumber>.+)\\|(?<pPassword>.+)\\|(?<name>.+)\\|(?<surname>.+)\\|(?<IDnumber>.+)\\|(?<DateOfBirth>.+)\\|(?<address>.+)\\|(?<studioID>.+)$"),
        REMOVE_USER("^remove-user#(?<username>.+)\\|(?<password>.+)\\|(?<userID>.+)$"),
        ADD_PAINTING(
                        "^add-painting#(?<managerID>.+)\\|(?<password>.+)\\|(?<ID>.+)\\|(?<name>.+)\\|(?<painter>.+)\\|(?<investor>.+)\\|(?<date>.+)\\|(?<copyNumber>.+)\\|(?<categoryID>.+)\\|(?<studioID>.+)$"),
        ADD_STATUE(
                        "^add-statue#(?<managerID>.+)\\|(?<password>.+)\\|(?<ID>.+)\\|(?<name>.+)\\|(?<sculptor>.+)\\|(?<professorName>.+)\\|(?<date>.+)\\|(?<category>.+)\\|(?<studioID>.+)$"),
        ADD_WORTHY(
                        "^add-worthy#(?<managerID>.+)\\|(?<password>.+)\\|(?<ID>.+)\\|(?<name>.+)\\|(?<painter>.+)\\|(?<printer>.+)\\|(?<date>.+)\\|(?<donator>.+)\\|(?<categoryID>.+)\\|(?<studioID>.+)$"),
        ADD_SELLING(
                        "^add-selling#(?<managerID>.+)\\|(?<password>.+)\\|(?<ID>.+)\\|(?<name>.+)\\|(?<painter>.+)\\|(?<printer>.+)\\|(?<date>.+)\\|(?<copyNumber>.+)\\|(?<price>.+)\\|(?<discount>.+)\\|(?<category>.+)\\|(?<studioID>.+)$"),
        REMOVE_ARTWORK("^remove-resource#(?<managerID>.+)\\|(?<password>.+)\\|(?<id>.+)\\|(?<studioID>.+)$"),
        BORROW("^borrow#(?<ID>.+)\\|(?<password>.+)\\|(?<studioID>.+)\\|(?<sourceID>.+)\\|(?<date>.+)\\|(?<hour>.+)$"),
        RETURN("^return#(?<ID>.+)\\|(?<password>.+)\\|(?<studioID>.+)\\|(?<sourceID>.+)\\|(?<date>.+)\\|(?<hour>.+)$"),
        ;

        public String regex;

        private Commands(String regex) {
                this.regex = regex;
        }
}
