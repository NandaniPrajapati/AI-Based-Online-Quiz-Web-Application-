

import React, { useEffect, useState } from "react";
function QuestionView(){

       const [quizData, setQuizData] = useState([]);
               const [currentQuestion, setCurrentQuestion] = useState(0);
               const [selectedOption, setSelectedOption] = useState("");
               const [score, setScore] = useState(0);
               const [showResult, setShowResult] = useState(false);

               // 🔗 Fetch questions from backend API
               useEffect(() => {
                 fetch("http://localhost:8080/api/questions") // change to your API
                   .then((res) => res.json())
                   .then((data) => {
                     setQuizData(data);
                   })
                   .catch((err) => console.error("Error fetching questions:", err));
               }, []);
      const handleOptionClick = (option) => {
         setSelectedOption(option);
       };

       const handleNext = () => {
         if (selectedOption === quizData[currentQuestion].answer) {
           setScore(score + 1);
         }

         setSelectedOption("");

         if (currentQuestion + 1 < quizData.length) {
           setCurrentQuestion(currentQuestion + 1);
         } else {
           setShowResult(true);
         }
       };

       if (quizData.length === 0) {
         return <h3 style={{ textAlign: "center" }}>Loading questions...</h3>;
       }


    return(

        <>

<div style={{ textAlign: "center", marginTop: "50px" }}>
      {showResult ? (
        <div>
          <h2>Quiz Result</h2>
          <p>
            Your Score: {score} / {quizData.length}
          </p>
          <button
            onClick={() => {
              setCurrentQuestion(0);
              setScore(0);
              setShowResult(false);
            }}
          >
            Restart Quiz
          </button>
        </div>
      ) : (
        <div>

            <h3>
                        Q{currentQuestion + 1}. {quizData[currentQuestion].question}
                      </h3>

                      {quizData[currentQuestion].options.map((option, index) => (
                        <div key={index}>
                          <label>
                            <input
                              type="radio"
                              name="option"
                              value={option}
                              checked={selectedOption === option}
                              onChange={() => handleOptionClick(option)}
                            />
                            {option}
                          </label>
                        </div>
                      ))}
                       <button onClick={handleNext} disabled={!selectedOption}>
                                  Next
                                </button>
                              </div>
                            )}
                          </div>


        </>
        );
    }
export default QuestionView;