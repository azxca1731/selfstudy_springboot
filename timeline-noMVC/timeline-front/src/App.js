import React from "react";
import styled from "styled-components";
import Post from "./Post";
import Input from "./Input";

const AppDiv = styled.div`
	padding: 30px;
	min-height: 100vh;
	background-color: skyblue;
	width: 100vw;
`;

function App() {
	const createPost = () =>
		[1, 2, 3, 4, 5, 6, 7, 8, 9].map(index => <Post key={index} />);
	return (
		<AppDiv>
			<h2 className="text-primary text-center mb-4">Timeliner!</h2>
			<Input />
			{createPost()}
		</AppDiv>
	);
}

export default App;
