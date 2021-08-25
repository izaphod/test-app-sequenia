package com.nimadugarov.testappsequenia.data.model

import com.google.gson.annotations.SerializedName
import com.nimadugarov.testappsequenia.domain.model.Genre

enum class GenreResponse {
    @SerializedName("драма")
    DRAMA {
        override fun asDomain(): Genre {
            return Genre.DRAMA
        }
    },

    @SerializedName("фэнтези")
    FANTASY {
        override fun asDomain(): Genre {
            return Genre.FANTASY
        }
    },

    @SerializedName("криминал")
    CRIME {
        override fun asDomain(): Genre {
            return Genre.CRIME
        }
    },

    @SerializedName("детектив")
    DETECTIVE {
        override fun asDomain(): Genre {
            return Genre.DETECTIVE
        }
    },

    @SerializedName("мелодрама")
    MELODRAMA {
        override fun asDomain(): Genre {
            return Genre.MELODRAMA
        }
    },

    @SerializedName("биография")
    BIOGRAPHY {
        override fun asDomain(): Genre {
            return Genre.BIOGRAPHY
        }
    },

    @SerializedName("комедия")
    COMEDY {
        override fun asDomain(): Genre {
            return Genre.COMEDY
        }
    },

    @SerializedName("боевик")
    ACTION {
        override fun asDomain(): Genre {
            return Genre.ACTION
        }
    },

    @SerializedName("мюзикл")
    MUSICAL {
        override fun asDomain(): Genre {
            return Genre.MUSICAL
        }
    },

    @SerializedName("приключения")
    ADVENTURE {
        override fun asDomain(): Genre {
            return Genre.ADVENTURE
        }
    },

    @SerializedName("ужасы")
    HORROR {
        override fun asDomain(): Genre {
            return Genre.HORROR
        }
    };

    abstract fun asDomain(): Genre
}