window.swaggerSpec={
  "openapi" : "3.0.3",
  "info" : {
    "title" : "Badges",
    "description" : "Show your badges on your pages\n***[@puiiyuen](https://github.com/puiiyuen)***",
    "contact" : {
      "email" : "patrick.cp97@gmail.com"
    },
    "license" : {
      "name" : "MIT",
      "url" : "https://opensource.org/licenses/MIT"
    },
    "version" : "1.0.0"
  },
  "externalDocs" : {
    "description" : "Read More",
    "url" : "https://badges.peiyuan.ch/docs"
  },
  "servers" : [ {
    "url" : "http://localhost:8080/"
  }, {
    "url" : "https://badges.peiyuan.ch/"
  } ],
  "tags" : [ {
    "name" : "leetcode",
    "description" : "Leetcode badges"
  } ],
  "components" : {
    "schemas" : {
      "ShieldBadge" : {
        "description" : "Shield Badge",
        "type" : "object",
        "properties" : {
          "category" : {
            "type" : "string"
          },
          "links" : {
            "type" : "array",
            "items" : {
              "$ref" : "#/components/schemas/ShieldBadgeSvg"
            }
          }
        }
      },
      "ShieldBadgeSvg" : {
        "description" : "Shield Badge SVG Only",
        "type" : "string",
        "format" : "byte"
      },
      "LeetCode" : {
        "description" : "LeetCode",
        "type" : "object",
        "properties" : {
          "username" : {
            "type" : "string"
          },
          "ranking" : {
            "type" : "integer"
          },
          "reputation" : {
            "type" : "integer"
          },
          "allQuestionsCount" : {
            "description" : "All Questions Count",
            "type" : "array",
            "items" : {
              "$ref" : "#/components/schemas/LeetCodeAllQuestionsCount"
            }
          },
          "acSubmissionNum" : {
            "type" : "array",
            "items" : {
              "$ref" : "#/components/schemas/LeetCodeSubmissionNum"
            }
          },
          "totalSubmissionNum" : {
            "type" : "array",
            "items" : {
              "$ref" : "#/components/schemas/LeetCodeSubmissionNum"
            }
          }
        }
      },
      "LeetCodeSubmissionNum" : {
        "type" : "object",
        "properties" : {
          "difficulty" : {
            "type" : "string"
          },
          "count" : {
            "type" : "integer"
          },
          "submissions" : {
            "type" : "integer"
          }
        }
      },
      "LeetCodeAllQuestionsCount" : {
        "type" : "object",
        "properties" : {
          "difficulty" : {
            "type" : "string"
          },
          "count" : {
            "type" : "integer"
          }
        }
      },
      "ShieldParams" : {
        "type" : "object",
        "properties" : {
          "style" : {
            "type" : "string"
          },
          "label" : {
            "type" : "string"
          },
          "labelColor" : {
            "type" : "string"
          },
          "color" : {
            "type" : "string"
          },
          "logo" : {
            "type" : "string"
          },
          "logoColor" : {
            "type" : "string"
          },
          "logoWidth" : {
            "type" : "string"
          },
          "link" : {
            "type" : "array",
            "items" : {
              "type" : "string"
            }
          }
        }
      }
    }
  },
  "paths" : {
    "/leetcode" : {
      "get" : {
        "tags" : [ "leetcode" ],
        "summary" : "get leetcode user stats",
        "description" : "Retrive user stats",
        "operationId" : "getLeetcodeUserStats",
        "parameters" : [ {
          "name" : "username",
          "in" : "query",
          "description" : "User Identifier",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Return all user Stats",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/LeetCode"
                }
              },
              "application/xml" : {
                "schema" : {
                  "$ref" : "#/components/schemas/LeetCode"
                }
              }
            }
          }
        }
      }
    },
    "/leetcode/{username}" : {
      "get" : {
        "tags" : [ "leetcode" ],
        "summary" : "get leetcode badges",
        "description" : "Retrive leetcode badges by user id",
        "operationId" : "getAllLeetcodeBadgesByUsername",
        "parameters" : [ {
          "name" : "username",
          "in" : "path",
          "description" : "User Identifier",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "shieldParams",
          "in" : "query",
          "description" : "Other Shield Parameters",
          "schema" : {
            "$ref" : "#/components/schemas/ShieldParams"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Return all leetcode badges",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/ShieldBadge"
                }
              },
              "application/xml" : {
                "schema" : {
                  "$ref" : "#/components/schemas/ShieldBadge"
                }
              }
            }
          }
        }
      }
    },
    "/leetcode/{username}/submission" : {
      "get" : {
        "tags" : [ "leetcode" ],
        "summary" : "get leetcode submission badge",
        "description" : "Retrive leetcode submission badge by username",
        "operationId" : "getLeetcodeBadgeSubmissionByUsername",
        "parameters" : [ {
          "name" : "username",
          "in" : "path",
          "description" : "User Identifier",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "difficulty",
          "in" : "query",
          "description" : "Difficulty of submission",
          "schema" : {
            "type" : "string",
            "default" : "all"
          }
        }, {
          "name" : "accepted",
          "in" : "query",
          "description" : "Distingush Acceped/Total submissions",
          "schema" : {
            "type" : "boolean",
            "default" : false
          }
        }, {
          "name" : "shieldParams",
          "in" : "query",
          "description" : "Other Shield Parameters",
          "schema" : {
            "$ref" : "#/components/schemas/ShieldParams"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Return submission badge",
            "content" : {
              "image/svg+xml" : {
                "schema" : {
                  "$ref" : "#/components/schemas/ShieldBadgeSvg"
                }
              }
            }
          }
        }
      }
    },
    "/leetcode/{username}/solved" : {
      "get" : {
        "tags" : [ "leetcode" ],
        "summary" : "get leetcode accepted problem badge",
        "description" : "Retrive badge by username",
        "operationId" : "getLeetcodeBadgeSolvedProblemByUsername",
        "parameters" : [ {
          "name" : "username",
          "in" : "path",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "difficulty",
          "in" : "query",
          "schema" : {
            "type" : "string",
            "default" : "all"
          }
        }, {
          "name" : "shieldParams",
          "in" : "query",
          "description" : "Other Shield Parameters",
          "schema" : {
            "$ref" : "#/components/schemas/ShieldParams"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Return accepted problem badge",
            "content" : {
              "image/svg+xml" : {
                "schema" : {
                  "$ref" : "#/components/schemas/ShieldBadgeSvg"
                }
              }
            }
          }
        }
      }
    },
    "/leetcode/{username}/ranking" : {
      "get" : {
        "tags" : [ "leetcode" ],
        "summary" : "Get Leetcode Ranking",
        "description" : "Retrive ranking badge by username",
        "operationId" : "getLeetcodeBadgeRankingByUsername",
        "parameters" : [ {
          "name" : "username",
          "in" : "path",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "shieldParams",
          "in" : "query",
          "description" : "Other Shield Parameters",
          "schema" : {
            "$ref" : "#/components/schemas/ShieldParams"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Return ranking badge",
            "content" : {
              "image/svg+xml" : {
                "schema" : {
                  "$ref" : "#/components/schemas/ShieldBadgeSvg"
                }
              }
            }
          }
        }
      }
    },
    "/leetcode/{username}/rate" : {
      "get" : {
        "tags" : [ "leetcode" ],
        "summary" : "Get Leetcode accepted rate",
        "description" : "Retrive accepted rate badge by username, problem category",
        "operationId" : "getLeetcodeBadgeAcceptedRateByUsername",
        "parameters" : [ {
          "name" : "username",
          "in" : "path",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "difficulty",
          "in" : "query",
          "schema" : {
            "type" : "string",
            "default" : "all"
          }
        }, {
          "name" : "shieldParams",
          "in" : "query",
          "description" : "Other Shield Parameters",
          "schema" : {
            "$ref" : "#/components/schemas/ShieldParams"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Return accepted rate badge",
            "content" : {
              "image/svg+xml" : {
                "schema" : {
                  "$ref" : "#/components/schemas/ShieldBadgeSvg"
                }
              }
            }
          }
        }
      }
    },
    "/leetcode/{username}/name" : {
      "get" : {
        "tags" : [ "leetcode" ],
        "summary" : "Get LeetCode User's name / real name",
        "description" : "Retrive name badge by username",
        "operationId" : "getLeetcodeBadgeNameByUsername",
        "parameters" : [ {
          "name" : "username",
          "in" : "path",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "shieldParams",
          "in" : "query",
          "description" : "Other Shield Parameters",
          "schema" : {
            "$ref" : "#/components/schemas/ShieldParams"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Return name badge",
            "content" : {
              "image/svg+xml" : {
                "schema" : {
                  "$ref" : "#/components/schemas/ShieldBadgeSvg"
                }
              }
            }
          }
        }
      }
    }
  }
}