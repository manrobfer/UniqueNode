const express = require("express");
const router = express.Router();
const User = require("./user");

router.get("/admin/user", (req, res) => {
    res.render("user/users");
});


router.get("/admin/user/create", (req, res) => {
    res.render("user/create");
});

router.post("/user/create", (req, res) => {
      var name = req.body.name;
      var password = req.body.password;
      var email = req.body.email;

      res.json({name,password, email});
});

module.exports = router;